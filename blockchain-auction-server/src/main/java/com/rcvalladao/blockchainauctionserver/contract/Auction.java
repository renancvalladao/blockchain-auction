package com.rcvalladao.blockchainauctionserver.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.8.
 */
@SuppressWarnings("rawtypes")
public class Auction extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162000cd738038062000cd7833981016040819052620000349162000284565b81518290600090819062000049908262000407565b506020820151600182019062000060908262000407565b506040820151600282018054606085015160ff93841661ffff19928316176101009185168202179092556080850151805160038601805460209384015192871690851617911515850291909117905560a0909501518051600490950180549190960151949093169216919091179115150217905560058054336001600160a01b0319909116179055620000f48142620004d3565b60085550620004fb9050565b634e487b7160e01b600052604160045260246000fd5b60405160c081016001600160401b03811182821017156200013b576200013b62000100565b60405290565b604051601f8201601f191681016001600160401b03811182821017156200016c576200016c62000100565b604052919050565b600082601f8301126200018657600080fd5b81516001600160401b03811115620001a257620001a262000100565b6020620001b8601f8301601f1916820162000141565b8281528582848701011115620001cd57600080fd5b60005b83811015620001ed578581018301518282018401528201620001d0565b506000928101909101919091529392505050565b805160ff811681146200021357600080fd5b919050565b6000604082840312156200022b57600080fd5b604080519081016001600160401b038111828210171562000250576200025062000100565b604052905080620002618362000201565b8152602083015180151581146200027757600080fd5b6020919091015292915050565b600080604083850312156200029857600080fd5b82516001600160401b0380821115620002b057600080fd5b908401906101008287031215620002c657600080fd5b620002d062000116565b825182811115620002e057600080fd5b620002ee8882860162000174565b8252506020830151828111156200030457600080fd5b620003128882860162000174565b602083015250620003266040840162000201565b6040820152620003396060840162000201565b60608201526200034d876080850162000218565b6080820152620003618760c0850162000218565b60a082015260209590950151949694955050505050565b600181811c908216806200038d57607f821691505b602082108103620003ae57634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156200040257600081815260208120601f850160051c81016020861015620003dd5750805b601f850160051c820191505b81811015620003fe57828155600101620003e9565b5050505b505050565b81516001600160401b0381111562000423576200042362000100565b6200043b8162000434845462000378565b84620003b4565b602080601f8311600181146200047357600084156200045a5750858301515b600019600386901b1c1916600185901b178555620003fe565b600085815260208120601f198616915b82811015620004a45788860151825594840194600190910190840162000483565b5085821015620004c35787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b80820180821115620004f557634e487b7160e01b600052601160045260246000fd5b92915050565b6107cc806200050b6000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c8063430ca46f1461005c5780637ae42ff9146100665780637e50bb83146100845780638e7ea5b2146100995780639979ef45146100c5575b600080fd5b6100646100d8565b005b61006e6101a2565b60405161007b9190610645565b60405180910390f35b61008c610393565b60405161007b91906106e3565b6100a1610408565b6040805182516001600160a01b03168152602092830151928101929092520161007b565b6100646100d3366004610743565b61048e565b6005546001600160a01b031633146101455760405162461bcd60e51b815260206004820152602560248201527f4f6e6c7920746865206f776e65722063616e2066696e6973682074686520617560448201526431ba34b7b760d91b60648201526084015b60405180910390fd5b6008544210156101935760405162461bcd60e51b8152602060048201526019602482015278105d58dd1a5bdb881a185cc81b9bdd08195b991959081e595d603a1b604482015260640161013c565b6009805460ff19166001179055565b6101f66040805160c08101825260608082526020808301829052600083850181905291830182905283518085018552828152808201839052608084015283518085019094528184528301529060a082015290565b60006040518060c00160405290816000820180546102139061075c565b80601f016020809104026020016040519081016040528092919081815260200182805461023f9061075c565b801561028c5780601f106102615761010080835404028352916020019161028c565b820191906000526020600020905b81548152906001019060200180831161026f57829003601f168201915b505050505081526020016001820180546102a59061075c565b80601f01602080910402602001604051908101604052809291908181526020018280546102d19061075c565b801561031e5780601f106102f35761010080835404028352916020019161031e565b820191906000526020600020905b81548152906001019060200180831161030157829003601f168201915b5050509183525050600282015460ff80821660208085019190915261010092839004821660408086019190915280518082018252600387015480851682528590048416151581840152606086015280518082019091526004909501548083168652929092041615159083015260800152919050565b6060600a805480602002602001604051908101604052809291908181526020016000905b828210156103ff576000848152602090819020604080518082019091526002850290910180546001600160a01b031682526001908101548284015290835290920191016103b7565b50505050905090565b604080518082019091526000808252602082015260095460ff1661046a5760405162461bcd60e51b8152602060048201526019602482015278105d58dd1a5bdb881a185cc81b9bdd08195b991959081e595d603a1b604482015260640161013c565b50604080518082019091526006546001600160a01b03168152600754602082015290565b60095460ff16156104e15760405162461bcd60e51b815260206004820152601960248201527f41756374696f6e2068617320616c726561647920656e64656400000000000000604482015260640161013c565b6000811161053d5760405162461bcd60e51b815260206004820152602360248201527f4269642076616c7565206d7573742062652067726561746572207468616e207a60448201526265726f60e81b606482015260840161013c565b60075481108061054d5750600754155b1561056a576007819055600680546001600160a01b031916331790555b6040805180820190915233815260208101918252600a805460018101825560009190915290517fc65a7bb8d6351c1cf70c95a316cc6a92839c986682d98bc35f958f4883f9d2a8600290920291820180546001600160a01b0319166001600160a01b0390921691909117905590517fc65a7bb8d6351c1cf70c95a316cc6a92839c986682d98bc35f958f4883f9d2a990910155565b6000815180845260005b8181101561062557602081850181015186830182015201610609565b506000602082860101526020601f19601f83011685010191505092915050565b602081526000825161010060208401526106636101208401826105ff565b90506020840151601f1984830301604085015261068082826105ff565b91505060ff604085015116606084015260ff606085015116608084015260808401516106be60a0850182805160ff1682526020908101511515910152565b5060a0840151805160ff1660e085015260208101511515610100850152509392505050565b602080825282518282018190526000919060409081850190868401855b828110156107365761072684835180516001600160a01b03168252602090810151910152565b9284019290850190600101610700565b5091979650505050505050565b60006020828403121561075557600080fd5b5035919050565b600181811c9082168061077057607f821691505b60208210810361079057634e487b7160e01b600052602260045260246000fd5b5091905056fea26469706673582212204a44754fe114f610465d9dd98e5741a2734193a8f365184baf1bc50a57925e7464736f6c63430008110033";

    public static final String FUNC_FINISHAUCTION = "finishAuction";

    public static final String FUNC_GETBIDHISTORY = "getBidHistory";

    public static final String FUNC_GETREQUIREMENTS = "getRequirements";

    public static final String FUNC_GETWINNER = "getWinner";

    public static final String FUNC_PLACEBID = "placeBid";

    @Deprecated
    protected Auction(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Auction(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Auction(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Auction(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> finishAuction() {
        final Function function = new Function(
                FUNC_FINISHAUCTION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getBidHistory() {
        final Function function = new Function(FUNC_GETBIDHISTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bid>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<Requirements> getRequirements() {
        final Function function = new Function(FUNC_GETREQUIREMENTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Requirements>() {}));
        return executeRemoteCallSingleValueReturn(function, Requirements.class);
    }

    public RemoteFunctionCall<WinnerInfo> getWinner() {
        final Function function = new Function(FUNC_GETWINNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<WinnerInfo>() {}));
        return executeRemoteCallSingleValueReturn(function, WinnerInfo.class);
    }

    public RemoteFunctionCall<TransactionReceipt> placeBid(BigInteger value) {
        final Function function = new Function(
                FUNC_PLACEBID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Auction load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Auction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Auction load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Auction(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Auction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Auction(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Auction> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Auction> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Auction> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Auction> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class OptionalRequirement extends StaticStruct {
        public BigInteger value;

        public Boolean required;

        public OptionalRequirement(BigInteger value, Boolean required) {
            super(new org.web3j.abi.datatypes.generated.Uint8(value), 
                    new org.web3j.abi.datatypes.Bool(required));
            this.value = value;
            this.required = required;
        }

        public OptionalRequirement(Uint8 value, Bool required) {
            super(value, required);
            this.value = value.getValue();
            this.required = required.getValue();
        }
    }

    public static class Bid extends StaticStruct {
        public String bidder;

        public BigInteger value;

        public Bid(String bidder, BigInteger value) {
            super(new org.web3j.abi.datatypes.Address(160, bidder), 
                    new org.web3j.abi.datatypes.generated.Uint256(value));
            this.bidder = bidder;
            this.value = value;
        }

        public Bid(Address bidder, Uint256 value) {
            super(bidder, value);
            this.bidder = bidder.getValue();
            this.value = value.getValue();
        }
    }

    public static class WinnerInfo extends StaticStruct {
        public String bidderAddress;

        public BigInteger cost;

        public WinnerInfo(String bidderAddress, BigInteger cost) {
            super(new org.web3j.abi.datatypes.Address(160, bidderAddress), 
                    new org.web3j.abi.datatypes.generated.Uint256(cost));
            this.bidderAddress = bidderAddress;
            this.cost = cost;
        }

        public WinnerInfo(Address bidderAddress, Uint256 cost) {
            super(bidderAddress, cost);
            this.bidderAddress = bidderAddress.getValue();
            this.cost = cost.getValue();
        }
    }

    public static class Requirements extends DynamicStruct {
        public String vnfName;

        public String vnfType;

        public BigInteger numCpus;

        public BigInteger memSize;

        public OptionalRequirement maxDelay;

        public OptionalRequirement bandwidth;

        public Requirements(String vnfName, String vnfType, BigInteger numCpus, BigInteger memSize, OptionalRequirement maxDelay, OptionalRequirement bandwidth) {
            super(new org.web3j.abi.datatypes.Utf8String(vnfName), 
                    new org.web3j.abi.datatypes.Utf8String(vnfType), 
                    new org.web3j.abi.datatypes.generated.Uint8(numCpus), 
                    new org.web3j.abi.datatypes.generated.Uint8(memSize), 
                    maxDelay, 
                    bandwidth);
            this.vnfName = vnfName;
            this.vnfType = vnfType;
            this.numCpus = numCpus;
            this.memSize = memSize;
            this.maxDelay = maxDelay;
            this.bandwidth = bandwidth;
        }

        public Requirements(Utf8String vnfName, Utf8String vnfType, Uint8 numCpus, Uint8 memSize, OptionalRequirement maxDelay, OptionalRequirement bandwidth) {
            super(vnfName, vnfType, numCpus, memSize, maxDelay, bandwidth);
            this.vnfName = vnfName.getValue();
            this.vnfType = vnfType.getValue();
            this.numCpus = numCpus.getValue();
            this.memSize = memSize.getValue();
            this.maxDelay = maxDelay;
            this.bandwidth = bandwidth;
        }
    }
}
